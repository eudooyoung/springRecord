package com.multi.restapi.section04.hateoas;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/*Spring HATEOAS에서는 RepresentationModelAssembler 인터페이스를 제공한다.
 * 이 템플릿 인터페이스는 toModel, toCollectionModel 메서드만 정의하고 있다.
 * 말 그대로 매개변수로 받은 객체를 RepresentationModel로 변환하는 역할이며 앞서 사용했던 EntityModel이
 * 이 RepresentationModel을 상속 해서 재구현 하여 사용 */

@Component
public class DtoModelAssembler implements RepresentationModelAssembler<UserDTO, EntityModel<UserDTO>> {
    @Override
    public EntityModel<UserDTO> toModel(UserDTO dto) {

        return EntityModel.of(dto,
                linkTo(methodOn(HateoasTestController.class).findUserByNo(dto.getNo())).withSelfRel(), // 셀프
                linkTo(methodOn(HateoasTestController.class).findAllUsers()).withRel("users")); //경로 작성


    }
}