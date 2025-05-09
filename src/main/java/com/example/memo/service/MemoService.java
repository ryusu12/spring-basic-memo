package com.example.memo.service;

import com.example.memo.dto.MemoRequestDto;
import com.example.memo.dto.MemoResponseDto;

import java.util.List;

public interface MemoService {
    MemoResponseDto saveMemo(MemoRequestDto requestDto);

    List<MemoResponseDto> findAllMemos();

    MemoResponseDto findMemoById(Long id);

    MemoResponseDto updateMemoById(Long id, MemoRequestDto dto);

    MemoResponseDto updateTitle(Long id, MemoRequestDto dto);

    void deleteMemoById(Long id);
}