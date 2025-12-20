package org.magasbook.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.magasbook.dto.mapper.UserMapper;
import org.magasbook.dto.userdto.UserCreateDTO;
import org.magasbook.dto.userdto.UserUpdateDTO;
import org.magasbook.enums.UserRoles;
import org.magasbook.models.UserEntity;
import org.magasbook.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;


@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserEntity createUser(UserCreateDTO dto) {

        // Uniqueness check
        if (userRepository.existsByNickname(dto.nickname())) {
            throw new IllegalStateException("Nickname has already used");
        }
        if (userRepository.existsByNumberPhone(dto.numberPhone())) {
            throw new IllegalStateException("Number phone has already used");
        }

        UserEntity createdUser = userMapper.toEntity(dto);
        createdUser.setPassword(
                passwordEncoder.encode(dto.password())
        );
        createdUser.setRole(UserRoles.AUTHENTICATED);
        return userRepository.save(createdUser);
    }

    @Transactional
    public void assignRole(Long adminId, Long userId) throws AccessDeniedException{
        UserEntity admin = userRepository.findById(adminId)
                .orElseThrow(() -> new EntityNotFoundException("Admin is not found"));
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User is not found"));

        if (admin.getRole() == UserRoles.SENIOR_ADMIN) {
            if (user.getRole() == UserRoles.AUTHENTICATED) {
                user.setRole(UserRoles.ADMIN);
            } else {
                user.setRole(UserRoles.AUTHENTICATED);
            }
        } else {
            throw new AccessDeniedException("Only SENIOR_ADMIN can assign roles");
        }

        userRepository.save(user);
    }

    public void updateUser(UserUpdateDTO dto) {
        UserEntity user = userRepository.findById(dto.id())
                .orElseThrow(() -> new EntityNotFoundException("User is not found"));

        userMapper.toUpdateEntity(dto, user);
        userRepository.save(user);
    }


}
