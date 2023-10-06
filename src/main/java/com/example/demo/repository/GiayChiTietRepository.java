package com.example.demo.repository;

import com.example.demo.model.ChiTietGiay;
import com.example.demo.viewModel.CTGViewModel;
import com.example.demo.model.Giay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GiayChiTietRepository extends JpaRepository<ChiTietGiay, UUID> {

    List<ChiTietGiay> findByGiay(Giay giay);

}
