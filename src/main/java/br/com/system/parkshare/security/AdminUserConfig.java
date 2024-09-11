package br.com.system.parkshare.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.parkshare.role.Role;
import br.com.system.parkshare.role.Role.Values;
import br.com.system.parkshare.role.RoleRepository;


@Configuration
public class AdminUserConfig implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        for (Values values : Role.Values.values()) {
            try {
                if (roleRepository.findByName(values) == null) {
                    Role role = new Role();
                    role.setName(values);
                    roleRepository.save(role);
                }
            } catch (Exception e) {
                Role role = new Role();
                role.setName(values);
                roleRepository.save(role);
            }
        }

    }

}
