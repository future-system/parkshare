package br.com.system.parkshare.accountPayment;

import br.com.system.parkshare.account.Account;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "account_payment")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPayment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_account", nullable = false)
    private Account account;

    @NotBlank
    private String name;

    @NotBlank
    private String subname;

    @NotBlank
    private int idType;

    @NotBlank
    private Type type;

    public enum Type {
        CREDIT,
        DEBIT,
        PIX,
    }
}
