// loadPagination(data.currentPage, data.totalPages, loadMenu)
function loadPagination(currentPage, totalPages, onPageClick) {
    const $pagination = $('#pagination');
    $pagination.html('');

    let paginationHtml = '<ul class="pagination">';
    for (let i = 1; i <= totalPages; i++) {
        if (i === currentPage) {
            paginationHtml += `<li class="active"><span>${i}</span></li>`;  // active 클래스 추가
        } else {
            paginationHtml += `<li><a href="javascript:void(0); " class="page-link" data-page="${i}">${i}</a></li>`;
            //"javascript:void(0);" :a 태그가 아무동작하지않도록방지  , 페이지 이동 등방지
            // data- 접두어를 사용하면 HTML 요소에 추가적인 정보를 저장
        }
    }
    paginationHtml += '</ul>';

    $pagination.html(paginationHtml);

    $('.page-link').on('click', function(){
        const page = $(this).data('page');
        if(typeof onPageClick === 'function'){
            onPageClick(page);
        }
    })
}