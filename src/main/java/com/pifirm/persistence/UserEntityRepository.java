package com.pifirm.persistence;

import com.pifirm.domain.dto.user.UserDto;
import com.pifirm.domain.exception.GeneralException;
import com.pifirm.domain.repository.UserRepository;
import com.pifirm.persistence.crud.CrudUser;
import com.pifirm.persistence.entity.UserEntity;
import com.pifirm.persistence.mapper.UserMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserEntityRepository implements UserRepository {

    private final CrudUser crudUser;
    private final UserMapper userMapper;

    @PersistenceContext
    private EntityManager em;

    public UserEntityRepository(CrudUser crudUser, UserMapper userMapper) {
        this.crudUser = crudUser;
        this.userMapper = userMapper;
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        if (crudUser.findFirstByEmail(userEntity.getEmail()).orElse(null) != null) {
            throw new GeneralException("email-already-registered", "El correo electrónico ya está registrado.");
        }

        return this.crudUser.save(userEntity);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return this.crudUser.findFirstByEmailAndActiveTrue(email);
    }

    @Override
    public org.springframework.data.domain.Page<UserDto> allActiveUsers(org.springframework.data.domain.Pageable pageable) {
        return this.crudUser.findAll(pageable).map(userMapper::toDto);
    }

    @Override
    public UserEntity update(UserEntity userEntity) {
        return this.crudUser.save(userEntity);
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return this.crudUser.findByIdAndActiveTrue(id);
    }



    @Override
    public List<UserEntity> findByDpiOrName(String dpi, String name) {
        String base = " FROM UserEntity u WHERE u.active = true";
        List<String> clauses = new ArrayList<>();

        if (dpi != null && !dpi.isBlank()) {
            clauses.add(" CAST(u.dpi AS text) LIKE :dpi ");
        }
        if (name != null && !name.isBlank()) {
            clauses.add(" (LOWER(u.firstname) LIKE :name OR LOWER(u.lastname) LIKE :name OR LOWER(CONCAT(u.firstname, ' ', u.lastname)) LIKE :name) ");
        }

        String where = base;
        if (!clauses.isEmpty()) {
            where += " AND " + String.join(" AND ", clauses);
        }

        String selectJpql = "SELECT u" + where + " ORDER BY u.id";
        jakarta.persistence.TypedQuery<UserEntity> query = em.createQuery(selectJpql, UserEntity.class);
        if (dpi != null && !dpi.isBlank()) query.setParameter("dpi", dpi + "%");
        if (name != null && !name.isBlank()) query.setParameter("name", name.toLowerCase() + "%");
        return query.getResultList();
    }
}
