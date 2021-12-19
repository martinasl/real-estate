package com.aacademy.realestate.service.serviceImpl;

import com.aacademy.realestate.exception.DuplicateRecordException;
import com.aacademy.realestate.exception.ResourceNotFoundException;
import com.aacademy.realestate.model.Floor;
import com.aacademy.realestate.repository.FloorRepository;
import com.aacademy.realestate.service.impl.FloorServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FloorServiceTest {

    @Mock
    private FloorRepository floorRepository;

    @InjectMocks
    private FloorServiceImpl floorServiceIpml;

    private Floor floor;

    @Test
    public void verifyFindAll(){

        when(floorRepository.findAll()).thenReturn(Collections.emptyList());
        floorServiceIpml.findAll();
        verify(floorRepository,times(1)).findAll();
    }

    @Test
    public void verifySave(){
        Floor floorSave = Floor.builder().build();
        when(floorRepository.save(any(Floor.class))).thenReturn(floorSave);
        floorServiceIpml.save(floorSave);
        verify(floorRepository, times(1)).save(floorSave);
    }

    @Test(expected = DuplicateRecordException.class)
    public void verifySaveDuplicateException() {
        when(floorRepository.save(any(Floor.class)))
                .thenThrow(DataIntegrityViolationException.class);

        floorServiceIpml.save(Floor.builder().build());
    }

    @Test
    public void verifyFindByNumber() {
        when(floorRepository.findByNumber(any(Integer.class)))
                .thenReturn(Optional.of(Floor.builder().build()));

        floorServiceIpml.findByNumber(1);
        verify(floorRepository, times(1)).findByNumber(1);

    }

    @Test(expected = ResourceNotFoundException.class)
    public void verifyByNumberException() {
        when(floorRepository.findByNumber(any(Integer.class)))
                .thenReturn(Optional.empty());

        floorServiceIpml.findByNumber(1);
    }

    @Test
    public void verifyFindById() {
        when(floorRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(Floor.builder().build()));

        floorServiceIpml.findById(1L);
        verify(floorRepository, times(1)).findById(1L);

    }

    @Test(expected = ResourceNotFoundException.class)
    public void verifyByIdException() {
        when(floorRepository.findById(any(Long.class)))
                .thenReturn(Optional.empty());

        floorServiceIpml.findById(1L);
    }

    @Test
    public void verifyDelete() {
        doNothing().when(floorRepository).deleteById(any(Long.class));

        floorServiceIpml.delete(1L);

        verify(floorRepository, times(1)).deleteById(1L);
    }
}
