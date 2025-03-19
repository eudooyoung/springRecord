package com.multi.shop.board.model.dao;

import com.multi.shop.board.model.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {

    List<BoardDTO> selectAll();

    int insertBoard(BoardDTO newBoard);

    BoardDTO selectBoard(int no);

    int updateBoard(BoardDTO updatedBoard);

    int deleteBoard(int no);

//    매개 변수가 하나만 있다면 상관 없지만, 두 개 이상이라면
//    @Param 어노테이션을 통해 넘겨주는 매개변수의 키 값을 지정해줘야 쿼리에서 적절히 쓸 수 있다.
    void updateCount(@Param("no") int no);
}