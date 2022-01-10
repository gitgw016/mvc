package util;

public class Criteria {

	private int page;		// 현재 페이지
	private int perPageNum;	// 한 페이지에 보여줄 게시물 수
	private int startRow;	// 검색할 시작 인덱스 번호
	
	// SELECT * FROM board ORDER BY num DESC
	// limit 5; // 정렬된 상태에서 5개만큼 게시물을 가져옴
	// limit 게시물 검색할 시작 인덱스 번호, 가져올 게시물 수;
	
	public Criteria() {
		this(1,10);
		System.out.println("Criteria 기본 생성자 호출");
	}
	
	public Criteria(int page, int perPageNum) {
		setPage(page);
		setPerPageNum(perPageNum);
		System.out.println("Criteria 생성자 호출");
	}

	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		if(page <= 0) {
			this.page = 1;
			return;
		}
		this.page = page;
	}
	public int getPerPageNum() {
		return perPageNum;
	}
	public void setPerPageNum(int perPageNum) {
		if(perPageNum <= 0 || perPageNum > 50) {
			this.perPageNum = 10;
			return;
		}
		this.perPageNum = perPageNum;
	}
	public int getStartRow() {
		this.startRow = (this.page-1) * perPageNum;
		return startRow;
	}
	
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + ", startRow=" + getStartRow() + "]";
	}
	
}
