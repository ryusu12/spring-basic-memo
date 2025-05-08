package com.example.memo.controller;

import com.example.memo.dto.MemoRequestDto;
import com.example.memo.dto.MemoResponseDto;
import com.example.memo.entity.Memo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/memos")
public class MemoController {
    // 임의의 DB
    private final Map<Long, Memo> memoList = new HashMap<>();

    // 메모 생성
    @PostMapping
    public ResponseEntity<MemoResponseDto> createMemo(@RequestBody MemoRequestDto dto) {
        // 식별자 id 증가하기
        Long memoId = memoList.isEmpty() ? 1 : Collections.max(memoList.keySet()) + 1;

        // DB 저장
        Memo memo = new Memo(memoId, dto.getTitle(), dto.getContents());
        memoList.put(memoId, memo);

        return new ResponseEntity<>(new MemoResponseDto(memo), HttpStatus.CREATED);
    }

    // 메모 전체 조회
    @GetMapping()
    public ResponseEntity<List<MemoResponseDto>> findAllMemos() {
        List<MemoResponseDto> responseList = memoList.values().stream().map(MemoResponseDto::new).toList();

        return new ResponseEntity<>(responseList, HttpStatus.CREATED);
    }

    // 메모 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<MemoResponseDto> findMemoById(@PathVariable Long id) {
        //id로 찾아서 반환
        Memo memo = memoList.get(id);
        if (memo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new MemoResponseDto(memo), HttpStatus.OK);
    }

    // 메모 수정
    @PutMapping("/{id}")
    public ResponseEntity<MemoResponseDto>  updateMemoById(
            @PathVariable Long id,
            @RequestBody MemoRequestDto dto
    ) {
        Memo memo = memoList.get(id);
        if (memo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // 필수값 검증
        if (dto.getTitle() == null || dto.getContents() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        memo.update(dto);
        return new ResponseEntity<>(new MemoResponseDto(memo), HttpStatus.OK);
    }

    // 메모 제목 수정
    @PatchMapping("/{id}")
    public ResponseEntity<MemoResponseDto> updateTitle(
            @PathVariable Long id,
            @RequestBody MemoRequestDto dto
    ) {
        Memo memo = memoList.get(id);
        if (memo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // 필수값 검증
        if (dto.getTitle() == null || dto.getContents() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        memo.updateTitle(dto);
        return new ResponseEntity<>(new MemoResponseDto(memo), HttpStatus.OK);
    }

    // 메모 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemoById(@PathVariable Long id) {
        // 식별자 id가 있는지 확인
        if (memoList.containsKey(id)) {
            memoList.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}