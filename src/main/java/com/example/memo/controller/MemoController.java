package com.example.memo.controller;

import com.example.memo.dto.MemoRequestDto;
import com.example.memo.dto.MemoResponseDto;
import com.example.memo.entity.Memo;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/memos")
public class MemoController {
    // 임의의 DB
    private final Map<Long, Memo> memoList = new HashMap<>();

    // 메모 생성
    @PostMapping
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto dto) {
        // 식별자 id 증가하기
        Long memoId = memoList.isEmpty() ? 1 : Collections.max(memoList.keySet()) + 1;

        // DB 저장
        Memo memo = new Memo(memoId, dto.getTitle(), dto.getContents());
        memoList.put(memoId, memo);

        return new MemoResponseDto(memo);
    }

    // 메모 단건 조회
    @GetMapping("/{id}")
    public MemoResponseDto findMemoById(@PathVariable Long id) {
        //id로 찾아서 반환
        Memo memo = memoList.get(id);
        return new MemoResponseDto(memo);
    }

    // 메모 수정
    @PutMapping("/{id}")
    public MemoResponseDto updateMemoById(
            @PathVariable Long id,
            @RequestBody MemoRequestDto dto
    ) {
        Memo memo = memoList.get(id);
        memo.update(dto);
        return new MemoResponseDto(memo);
    }

    // 메모 삭제
    @DeleteMapping("/{id}")
    public void deleteMemoById(@PathVariable Long id) {
        memoList.remove(id);
    }
}