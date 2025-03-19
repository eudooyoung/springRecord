package com.multi.shop.board.service;

import com.multi.shop.board.model.dao.BoardMapper;
import com.multi.shop.board.model.dto.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{

    private final BoardMapper boardMapper;

    @Autowired
    public BoardServiceImpl(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    @Override
    public List<BoardDTO> selectAll() {
        return boardMapper.selectAll();
    }

    @Override
    public BoardDTO selectBoard(int no) {
        try{
            updateCount(no);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return boardMapper.selectBoard(no);
    }

    @Override
    public int insertBoard(BoardDTO newBoard) {
        return boardMapper.insertBoard(newBoard);
    }

    @Override
    public int updateBoard(BoardDTO updatedBoard) {
        return boardMapper.updateBoard(updatedBoard);
    }

    @Override
    public int deleteBoard(int no) {
        return boardMapper.deleteBoard(no);
    }

    @Override
    public void updateCount(int no) {
        boardMapper.updateCount(no);
    }
}
