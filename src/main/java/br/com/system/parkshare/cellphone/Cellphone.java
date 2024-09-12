package br.com.system.parkshare.cellphone;

import br.com.system.parkshare.account.Account;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cellphone")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cellphone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCellphone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_account", nullable = false)
    private Account account;

    @NotBlank
    private String cellphone;

    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private boolean isPriority;

    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private boolean isActive;
}
