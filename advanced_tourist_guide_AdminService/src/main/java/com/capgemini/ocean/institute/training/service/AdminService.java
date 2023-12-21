package com.capgemini.ocean.institute.training.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.ocean.institute.training.dto.AdminLoginDto;
import com.capgemini.ocean.institute.training.entity.Admin;
import com.capgemini.ocean.institute.training.repo.AdminRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public Admin getAdminById(Long id) {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        return optionalAdmin.orElse(null);
    }

    public Admin updateAdmin(Long id, Admin updatedAdmin) {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        if (optionalAdmin.isPresent()) {
            Admin existingAdmin = optionalAdmin.get();
            existingAdmin.setName(updatedAdmin.getName());
            existingAdmin.setPassword(updatedAdmin.getPassword());
            return adminRepository.save(existingAdmin);
        }
        return null;
    }

    public boolean deleteAdmin(Long id) {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        if (optionalAdmin.isPresent()) {
            adminRepository.deleteById(id);
            return true;
        }
        return false;
    }

	public int loginAdmin(AdminLoginDto admin) {
		// TODO Auto-generated method stub
		Admin savedAdmin=adminRepository.findByEmail(admin.getUsername());
		if(savedAdmin == null)
			return 0; //not found
		
		if(savedAdmin.getPassword().equals(admin.getPassword()) == false) {
			return -1;
		}
		
		return 1;
	}
}
