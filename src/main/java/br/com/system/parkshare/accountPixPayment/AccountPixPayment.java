package br.com.system.parkshare.accountPixPayment;

import br.com.system.parkshare.accountPayment.AccountPayment;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "pix_key_type", nullable = false)
    private PixKey pixKey; 

    public enum PixKey {
        CPF,
        CNPJ,
        EMAIL,
        PHONE,
        RANDOM
    }
}
