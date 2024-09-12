package br.com.system.parkshare.accountPixPayment;

import br.com.system.parkshare.accountPayment.AccountPayment;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "account_pix_payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountPixPayment extends AccountPayment {

    @NotBlank
    @Column(name = "pix_key", nullable = false, unique = true)
    private String pixKey;

    @Enumerated(EnumType.STRING)
    @Column(name = "pix_key_type", nullable = false)
    private PixKeyType pixKeyType;

    @NotBlank
    private String name;

    @NotBlank
    private String subname;

    public enum PixKeyType {
        CPF,
        CNPJ,
        EMAIL,
        PHONE,
        RANDOM
    }
}
