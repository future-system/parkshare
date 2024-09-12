package br.com.system.parkshare.accountCardPayment;

import br.com.system.parkshare.accountPayment.AccountPayment;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "account_card_payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountCardPayment extends AccountPayment {

    @NotBlank
    private String name;

    @NotBlank
    private String subname;

    @NotBlank
    @Size(min = 16, max = 16, message = "O número do cartão deve ter 16 dígitos")
    @Column(name = "card_number", nullable = false, unique = true)
    private String cardNumber;

    @NotBlank
    @Pattern(regexp = "(0[1-9]|1[0-2])/([0-9]{2})", message = "A data de expiração deve estar no formato MM/YY")
    @Column(name = "expiry_date", nullable = false)
    private String expiryDate;

    @NotBlank
    @Size(min = 3, max = 3, message = "O CVV deve ter 3 dígitos")
    @Column(name = "cvv", nullable = false)
    private String cvv;

    @Enumerated(EnumType.STRING)
    @Column(name = "card_type", nullable = false)
    private CardType cardType;

    public enum CardType {
        DEBIT,
        CREDIT
    }
}
