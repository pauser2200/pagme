package br.pagme.domain.usuario.entidades;

import br.pagme.domain.EntidadePersistente;
import br.pagme.enums.RolesEnum;
import br.pagme.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Getter
@Setter
@Table(name = "USU_USUARIOS")
@AttributeOverride(name ="id",column = @Column(name = "USU_ID", columnDefinition = "BIGINT"))
public class Usuario extends EntidadePersistente<Long> implements UserDetails {

    @Column(name = "USU_DS_USERNAME", nullable = false, columnDefinition = "VARCHAR(20)")
    private String username;

    @Column(name = "USU_DS_PASSWORD", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "USU_DS_ROLE", nullable = false)
    private RolesEnum role;

    @Column(name = "USU_DS_NOME", nullable = false, columnDefinition = "VARCHAR(150)")
    private String nome;

    @Column(name = "USU_DS_EMAIL", nullable = false,unique = true, columnDefinition = "VARCHAR(100)")
    private String email;

    @Column(name = "USU_DS_TELEFONE", nullable = false, columnDefinition = "VARCHAR(11)")
    private String telefone;

    @Column(name = "USU_DS_STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @Column(name = "USU_DH_CRIACAO")
    private LocalDateTime criadoEm;

    @PrePersist
    protected void onCreate() {
        this.criadoEm = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Retorna a role. Ex: se for ADMIN, retorna uma authority "ROLE_ADMIN"
        // O Spring Security gosta do prefixo "ROLE_" para métodos hasRole()
        if (RolesEnum.ADMIN.equals(this.role)) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("SCOPE_ADMIN")
            );
        }
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

}
