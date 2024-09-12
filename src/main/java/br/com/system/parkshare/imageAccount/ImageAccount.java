package br.com.system.parkshare.imageAccount;

import br.com.system.parkshare.account.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "image_account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_account", nullable = false)
    private Account account;

    @Lob
    private byte[] image;
}
