package util;

public class PageMaker {

	private int totalCount;		// 전체 게시물 수
	private int startPage;		// 게시물 화면에 보여질 시작 페이지 번호
	private int endPage;		// 게시물 화면에 보여질 마지막 페이지 번호
	private boolean prev;		// 이전 페이지 버튼 활성화 여부
	private boolean next;		// 다음 페이지 버튼 활성화 여부
	private int displayNum = 5;	// 한번에 보여줄 페이지 번호 개수
	private int maxPage;		// 최대 페이지 블럭 개수
	private boolean start;		// 처음 페이지 버튼 활성화 여부
	private boolean last;		// 마지막 페이지 버튼 활성화 여부
	
	Criteria cri;				// 기준 페이지 정보
	
	public void calcPaging() {
		endPage = (int)Math.ceil(cri.getPage()/(double)displayNum)*displayNum;
		startPage = endPage - displayNum + 1;
		maxPage = (int)(Math.ceil(totalCount/(double)cri.getPerPageNum()));
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		start = cri.getPage() == 1 ? false : true;
		prev = (endPage - displayNum <= 0) ? false : true;
		next = (endPage < maxPage) ? true : false;
		last = (endPage == maxPage) ? false : true;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcPaging();
	}

	public int getDisplayNum() {
		return displayNum;
	}

	public void setDisplayNum(int displayNum) {
		this.displayNum = displayNum;
		calcPaging();
	}

	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
		calcPaging();
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public boolean isNext() {
		return next;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public boolean isStart() {
		return start;
	}

	public boolean isLast() {
		return last;
	}
	
	public String makeSearchQuery(int page) {
		StringBuilder sb = new StringBuilder();
		// /contextPath/noticeSearch.do?page=1&searchName=title&searchValue=게시글
		sb.append("?");
		sb.append("page=" +page);
		sb.append("&perPageNum="+cri.getPerPageNum());
		if(cri instanceof SearchCriteria) {
			SearchCriteria scri = (SearchCriteria)cri;
			if(scri.getSearchName() != null) {
				sb.append("&searchName="+scri.getSearchName());
				sb.append("&searchValue="+scri.getSearchValue());
			}
		}
		String queryString = sb.toString();
		System.out.println(queryString);
		return queryString;
	}

	@Override
	public String toString() {
		return "PageMaker [totalCount=" + totalCount + ", startPage=" + startPage + ", endPage=" + endPage + ", prev="
				+ prev + ", next=" + next + ", displayNum=" + displayNum + ", maxPage=" + maxPage + ", start=" + start
				+ ", last=" + last + ", cri=" + cri + "]";
	}
	
}
