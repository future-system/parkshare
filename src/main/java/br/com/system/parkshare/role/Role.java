package br.com.system.parkshare.role;

import java.util.Collection;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;

import br.com.system.parkshare.privilege.Privilege;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "role")
@Entity(name = "role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idRole;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private Values name;

    @ManyToMany
    @JoinTable(name = "roles_privileges", joinColumns = @JoinColumn(name = "id_role"), inverseJoinColumns = @JoinColumn(name = "id_privilege"))
    private Collection<Privilege> privileges;

    public enum Values implements GrantedAuthority {
        ROLE_ADMIN("ADMIN"),
        ROLE_APP("APP"),
        ROLE_COMPANY("COMPANY"),
        ROLE_ESTABLISHMENT("ESTABLISHMENT"),
        ROLE_STAFF("STAFF"),
        ROLE_USER("USER"),
        ROLE_GUEST("GUEST"),
        ROLE_NONE("NONE");

        private final String role;

        Values(String role) {
            this.role = role;
        }

        @Override
        public String getAuthority() {
            return role;
        }
    }
}
