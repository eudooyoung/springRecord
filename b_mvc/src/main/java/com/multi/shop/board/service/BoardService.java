package com.multi.shop.board.service;

import com.multi.shop.board.model.dto.BoardDTO;

import java.util.List;

public interface BoardService {

    List<BoardDTO> selectAll() ;

    int insertBoard(BoardDTO newBoard);

    BoardDTO selectBoard(int no);

    int updateBoard(BoardDTO updatedBoard);

    int deleteBoard(int no);

    void updateCount(int no);
}
