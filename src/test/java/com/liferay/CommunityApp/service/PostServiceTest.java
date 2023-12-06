package com.liferay.CommunityApp.service;

import com.liferay.CommunityApp.exceptions.CommunityException;
import com.liferay.CommunityApp.exceptions.CustomAuthenticationException;
import com.liferay.CommunityApp.models.CommunityModel;
import com.liferay.CommunityApp.models.PostModel;
import com.liferay.CommunityApp.models.UserModel;
import com.liferay.CommunityApp.repositories.CommunityRepository;
import com.liferay.CommunityApp.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private CommunityRepository communityRepository;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private PostService postService;

    @BeforeEach
    public void setUp() {
        // Configurações dos mocks
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        // Adicione aqui outras configurações necessárias
    }

    @Test
    void testCreatePost_UserNotMemberOfCommunity() throws CustomAuthenticationException, CommunityException {

        when(authentication.isAuthenticated()).thenReturn(true);
        UserModel mockUserModel = mock(UserModel.class);
        when(authentication.getPrincipal()).thenReturn(mockUserModel);

        when(communityRepository.findByName(anyString())).thenReturn(Optional.of(new CommunityModel()));

        PostModel postModel = new PostModel();


        assertThrows(CommunityException.class, () -> {
            postService.createPost(postModel, "Community Name");
        });

        verify(postRepository, never()).save(any(PostModel.class));
    }

    @Test
    void updatePost() {
    }

    @Test
    void deletePost() {
    }

    @Test
    void findAll() {
    }
}