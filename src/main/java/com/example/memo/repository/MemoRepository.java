package com.example.memo.repository;

import com.example.memo.dto.MemoRequestDto;
import com.example.memo.dto.MemoResponseDto;
import com.example.memo.entity.Memo;

import java.util.List;

public interface MemoRepository {
    MemoResponseDto saveMemo(Memo memo);

    List<MemoResponseDto> findAllMemos();

    Memo findMemoByIdOrElseThrow(Long id);

    int updateMemo(Long id, MemoRequestDto dto);

    int updateMemoTitle(Long id, MemoRequestDto dto);

    int deleteMemoById(Long id);
}