package br.com.system.parkshare.account;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.system.parkshare.imageAccount.ImageAccount;
import br.com.system.parkshare.accountPayment.AccountPayment;
import br.com.system.parkshare.cellphone.Cellphone;
import br.com.system.parkshare.role.Role;
import br.com.system.parkshare.security.AuthDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "account")
@Entity(name = "account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idAccount;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "accounts_roles", joinColumns = @JoinColumn(name = "id_account"), inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Set<Role> roles;

    @NotBlank
    @CPF
    @Column(unique = true)
    private String cpf;
    
    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    private String nickname;
    
    @NotBlank
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    @NotBlank
    private Date birthday;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Cellphone> cellphones;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private ImageAccount imageAccount;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<AccountPayment> payments;
    
    @CreationTimestamp
    private LocalDateTime dateTimeCreated;
    
    @UpdateTimestamp
    private LocalDateTime dateTimeUpdated;
    
    @CreationTimestamp
    private LocalDateTime dateTimeAccess;

    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private boolean isActive;

    @Override
    public Collection<SimpleGrantedAuthority> getAuthorities() {
        return getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getName().name()))
        .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean isLoginCorrect(AuthDTO auth, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(auth.password(), this.password);
    }

    @Override
    public String getUsername() {
        return nickname;
    }
}
