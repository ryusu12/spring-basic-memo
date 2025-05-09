package com.example.memo.repository;

import com.example.memo.dto.MemoResponseDto;
import com.example.memo.entity.Memo;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemoRepositoryImpl implements MemoRepository {
    // 임의의 DB
    private final Map<Long, Memo> memoList = new HashMap<>();

    @Override
    public Memo saveMemo(Memo memo) {
        // 식별자 id 증가하기
        Long memoId = memoList.isEmpty() ? 1 : Collections.max(memoList.keySet()) + 1;
        memo.setId(memoId);
        memoList.put(memoId, memo);

        return memo;
    }

    @Override
    public List<MemoResponseDto> findAllMemos() {
        return memoList.values().stream().map(MemoResponseDto::new).toList();
    }

    @Override
    public Memo findMemoById(Long id) {
        return memoList.get(id);
    }

    @Override
    public void deleteMemoById(Long id) {
        memoList.remove(id);
    }
}