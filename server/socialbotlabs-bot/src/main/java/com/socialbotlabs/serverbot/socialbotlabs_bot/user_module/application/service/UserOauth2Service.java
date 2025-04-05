package com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.application.service;

import com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.domain.model.Role;
import com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.insfrastructure.adapter.persistence.role.RoleRepositoryAdapter;
import com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.domain.model.User;
import com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.domain.model.UserOauth2;
import com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.infrastructure.adapter.persistence.UserOauth2RepositoryAdapter;
import com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.infrastructure.adapter.persistence.UserRepositoryAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserOauth2Service {

    private static final String GOOGLE_PROVIDER = "google-idp";
    private static final String GITHUB_PROVIDER = "github-idp";

    @Value("${social.botty.default.role}")
    private String defaultRole;

    private final UserOauth2RepositoryAdapter userOauth2RepositoryAdapter;
    private final RoleRepositoryAdapter roleRepositoryAdapter;
    private final UserRepositoryAdapter userRepositoryAdapter;
    private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    public UserOauth2Service(UserOauth2RepositoryAdapter userOauth2RepositoryAdapter,
                             UserRepositoryAdapter userRepositoryAdapter,
                             RoleRepositoryAdapter roleRepositoryAdapter,
                             OAuth2AuthorizedClientService oAuth2AuthorizedClientService) {
        this.roleRepositoryAdapter = roleRepositoryAdapter;
        this.userOauth2RepositoryAdapter = userOauth2RepositoryAdapter;
        this.userRepositoryAdapter = userRepositoryAdapter;
        this.oAuth2AuthorizedClientService = oAuth2AuthorizedClientService;
    }

    public boolean existsOauth2User(OAuth2User user) {
        if (getCurrentAuthentication() instanceof OAuth2AuthenticationToken oAuth2AuthenticationToken){
            String provider = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
            String providerUserId = getProviderId(user, provider);
            return userOauth2RepositoryAdapter.existsByProviderAndProviderUserId(provider, providerUserId);
        }
        return false;
    }

    public void registerNewOauth2User(OAuth2User user) {
        if (getCurrentAuthentication() instanceof OAuth2AuthenticationToken oAuth2AuthenticationToken){
            String provider = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
            if (provider.contains(GOOGLE_PROVIDER)){
                User userFromGoogle = getUserFromOauth2Google(user);
                User userSave = userRepositoryAdapter.findByEmail(userFromGoogle.getEmail())
                    .orElseGet(() -> userRepositoryAdapter.saveExternalUser(userFromGoogle));
                UserOauth2 userOauth2 = createUserFromOauth2Google(user);
                userOauth2.setUser(userSave);
                userOauth2RepositoryAdapter.saveUserOauth2(userOauth2);
                return;
            }
            if (provider.contains(GITHUB_PROVIDER)) {
                User userFromGithub = getUserFromOauth2Github(user,oAuth2AuthenticationToken);
                User userSave = userRepositoryAdapter.findByEmail(userFromGithub.getEmail())
                    .orElseGet(()-> userRepositoryAdapter.saveExternalUser(userFromGithub));
                UserOauth2 userOauth2 = createUserFromOauth2Github(user);
                userOauth2.setUser(userSave);
                userOauth2RepositoryAdapter.saveUserOauth2(userOauth2);
            }
        }
    }

    private Authentication getCurrentAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    private String getProviderId(OAuth2User user, String provider){
        if (provider.contains(GOOGLE_PROVIDER)){
            return user.getAttribute("sub");
        }
        if (provider.contains(GITHUB_PROVIDER)){
            Object idObject = user.getAttribute("id");
            return idObject != null ? idObject.toString() : null;
        }
        return null;
    }

    private User getUserFromOauth2Google(OAuth2User oAuth2User){
        User user = new User();
        user.setEmail(oAuth2User.getAttribute("email"));
        user.setFullName(oAuth2User.getAttribute("name"));
        String username = Objects.requireNonNull(oAuth2User.getAttribute("name")) +
            Objects.requireNonNull(oAuth2User.getAttribute("sub")).toString();
        user.setUsername(username);
        Role role = roleRepositoryAdapter.findByRoleName(defaultRole)
            .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRole(role);
        return user;
    }

    private UserOauth2 createUserFromOauth2Google(OAuth2User oAuth2User){
        UserOauth2 userOauth2 = new UserOauth2();
        userOauth2.setProvider(GOOGLE_PROVIDER);
        userOauth2.setProviderId(getProviderId(oAuth2User,GOOGLE_PROVIDER));
        userOauth2.setProfilePicture(oAuth2User.getAttribute("picture"));
        return userOauth2;
    }

    private User getUserFromOauth2Github(OAuth2User oAuth2User,OAuth2AuthenticationToken oAuth2AuthenticationToken){
        User user = new User();
        user.setEmail(fetchPrimaryGithubEmail(getAccessTokenFromOauth2Github(oAuth2AuthenticationToken)));
        user.setFullName(oAuth2User.getAttribute("name"));
        String username = Objects.requireNonNull(oAuth2User.getAttribute("name")) +
            Objects.requireNonNull(oAuth2User.getAttribute("id")).toString();
        user.setUsername(username);
        Role role = roleRepositoryAdapter.findByRoleName(defaultRole)
           .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRole(role);
        return user;
    }

    private UserOauth2 createUserFromOauth2Github(OAuth2User oAuth2User){
        UserOauth2 userOauth2 = new UserOauth2();
        userOauth2.setProvider(GITHUB_PROVIDER);
        Object idObject = oAuth2User.getAttribute("id");
        userOauth2.setProviderId(idObject != null ? idObject.toString() : null);
        userOauth2.setProfilePicture(oAuth2User.getAttribute("avatar_url"));
        return userOauth2;
    }

    private String getAccessTokenFromOauth2Github(OAuth2AuthenticationToken oAuth2AuthenticationToken){
        OAuth2AuthorizedClient authorizedClient = oAuth2AuthorizedClientService.loadAuthorizedClient(
            oAuth2AuthenticationToken.getAuthorizedClientRegistrationId(),
            oAuth2AuthenticationToken.getName()
        );
        if (authorizedClient != null) {
            return authorizedClient.getAccessToken().getTokenValue();
        }
        return null;
    }

    private String fetchPrimaryGithubEmail(String accessToken) {
        String url = "https://api.github.com/user/emails";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("Accept", "application/vnd.github.v3+json");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, entity, List.class);
        List<Map<String, Object>> emails = response.getBody();

        if (emails != null) {
            for (Map<String, Object> emailEntry : emails) {
                Boolean isPrimary = (Boolean) emailEntry.get("primary");
                if (Boolean.TRUE.equals(isPrimary)) {
                    return (String) emailEntry.get("email");
                }
            }
        }
        return null;
    }
}
