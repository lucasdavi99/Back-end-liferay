//package com.liferay.CommunityApp.configs;
//
//import com.liferay.CommunityApp.enums.CommunityPrivate;
//import com.liferay.CommunityApp.enums.UserRole;
//import com.liferay.CommunityApp.models.CommunityModel;
//import com.liferay.CommunityApp.models.UserModel;
//import com.liferay.CommunityApp.repositories.CommunityRepository;
//import com.liferay.CommunityApp.repositories.PostRepository;
//import com.liferay.CommunityApp.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//
//@Configuration
//@Profile("test")
//public class TestConfig implements CommandLineRunner {
//
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private CommunityRepository communityRepository;
//    @Autowired
//    private PostRepository postRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        //criptografando as senhas
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String encodedPasswordUser1 = encoder.encode("12345");
//        String encodedPasswordUser2 = encoder.encode("67890");
//        String encodedPasswordUser3 = encoder.encode("12345");
//
//        //usuários de teste
//        UserModel u1 = new UserModel(null, "lacoste","kervensilva@gmail.com", encodedPasswordUser1, "Kerven Kildhery", "Engenheiro de Software","Me chamo kerven, estudante de T.I" ,"Recife - PE", UserRole.USER, null, null, null);
//        UserModel u2 = new UserModel(null, "irineu","lucasdavi@gmail.com", encodedPasswordUser2, "Lucas Davi", "Arquiteto de Software", "Me chamo lucas, estudante de T.I" ,"Recife - PE", UserRole.USER, null, null, null);
//        UserModel u3 = new UserModel(null, "belligol","julio@gmail.com", encodedPasswordUser3, "Julio Cesar", "Dono de Software", "Me chamo julio, estudante de moda" ,"Recife - PE", UserRole.USER, null, null, null);
////
////        UserModel u1 = new UserModel(null, "lacoste", "lacoste@gmail.com", encodedPasswordUser1, "Lacoste da Silva",UserRole.ADMIN);
////        UserModel u2 = new UserModel(null, "irineu", "vocenaosabenemeu@gmail.com", encodedPasswordUser2, "Irineu Naosabe",UserRole.USER);
////        UserModel u3 = new UserModel(null, "belligol", "teste@gmail.com", encodedPasswordUser3, "Bellingham",UserRole.USER);
//
//        userRepository.saveAll(Arrays.asList(u1, u2,u3));
//
//        //comunidades de teste
//        CommunityModel c1 = new CommunityModel(null, "Os ingleses do Recife", "Grupo para amadores da língua inglesa (proficiência britânica) localizado no Recife, Pernambuco", u2,UserRole.ADMIN, CommunityPrivate.PUBLIC, LocalDate.parse("2020-12-08"), Arrays.asList(u1), null, null);
//        CommunityModel c2 = new CommunityModel(null, "Os franceses do Recife", "Grupo para amadores da língua francesa (proficiência europeia) localizado no Recife, Pernambuco", u2,UserRole.ADMIN, CommunityPrivate.PUBLIC, LocalDate.parse("2020-12-08"), Arrays.asList(u1), null, null);
//
//
//        communityRepository.saveAll(Arrays.asList(c1, c2));
//    }
//}
