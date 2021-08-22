package tablepagination;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ���������̼��� ���� ���� ����Ʈ ������
 *
 * ��� ���:
 * 1. �ν��Ͻ� ����: new Paginator(Integer pagesPerBlock, Integer postsPerPage, Long totalPostCount)
 *  - pagesPerBlock = �� ���� �� ������ ���� (��: 5�� ��� �� ���� [1 2 3 4 5] ������ ǥ��)
 *  - postsPerPage = ������ �ϳ� �� �������� Post(row)�� ����
 *  - totalPostCount = ���̺� ��ϵ� �� Post ����
 *  - ���� �������� setter/getter�� ������ �ֽ��ϴ�.
 *
 * 2. getTotalLastPageNum() ���� �� ������ ������ Ȯ���մϴ�.
 *
 * 3. getFixedBlock(Integer currentPageNum) �Ǵ� getElasticBlock(Integer currentPageNum)���� ������ ����Ʈ �����մϴ�.
 *  - currentNum = ���� ������
 *  - ����� Map<String, Object> ���·� ��ȯ�Ǹ�, pageListŰ�� ���� ������ ����Ʈ�Դϴ�.
 *  - ��) {totalPostCount=5555, isPrevExist=false, isNextExist=true, blockLastPageNum=11, postsPerPage=12,
 *        totalLastPageNum=462, currentPageNum=1, pagesPerBlock=11, pageList=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11],
 *        blockFirstPageNum=1}
 *
 * 4. getFixedBlock()�� ���� ������ ��ġ�� �׻� �����Ǿ� ������, getElasticBlock()�� ���� �������� �����ϸ� ���� �Ѱ�� ��ġ�ϵ��� �մϴ�.
 *  - ��) ������������ 6�̰� ���� ������ ���� 5�� ��� getFixedBlock()������ [6, 7, 8, 9, 10]�� ǥ�õ�����,
 *       getElasticBlock()�� ��� [4, 5, 6, 7, 8]�� ǥ�õ˴ϴ�.
 *  - getElasticBlock()�� pagesPerBlock�� Ȧ���� ������ ����� �� �ֽ��ϴ�.
 *
 */
public class tablepagination {

    // ����
    private Integer pagesPerBlock;
    private Integer postsPerPage;
    private Integer totalPostCount;

    private Integer totalLastPageNum;

    public tablepagination(Integer pagesPerBlock, Integer postsPerPage, Integer totalPostCount) {
        this.pagesPerBlock = pagesPerBlock;
        this.postsPerPage = postsPerPage;
        this.totalPostCount = totalPostCount;

        this.setTotalLastPageNum();
    }

    public Integer getPagesPerBlock() {
        return pagesPerBlock;
    }

    public Integer getPostsPerPage() {
        return postsPerPage;
    }

    public Integer getTotalPostCount() {
        return totalPostCount;
    }

    public Integer getTotalLastPageNum() {
        return this.totalLastPageNum;
    }

    public void setPagesPerBlock(Integer pagesPerBlock) {
        this.pagesPerBlock = pagesPerBlock;
    }

    public void setPostsPerPage(Integer postsPerPage) {
        this.postsPerPage = postsPerPage;
        this.setTotalLastPageNum();
    }

    public void setTotalPostCount(Integer totalPostCount) {
        this.totalPostCount = totalPostCount;
        this.setTotalLastPageNum();
    }

    private void setTotalLastPageNum() {
        // �� �Խñ� ���� �������� �� ������ ������ ��ȣ ���
        // totalPostCount �� 0�� ��� 1�������� ����
        if(totalPostCount == 0) {
            this.totalLastPageNum = 1;
        } else {
            this.totalLastPageNum = (int) (Math.ceil((double)totalPostCount / postsPerPage));
        }
    }

    private Map<String, Object> getBlock(Integer currentPageNum,
                                              Boolean isFixed) {

        if(pagesPerBlock % 2 == 0 && !isFixed) {
            throw new IllegalStateException("getElasticBlock: pagesPerBlock�� Ȧ���� �����մϴ�.");
        }

        if(currentPageNum > totalLastPageNum && totalPostCount != 0) {
            throw new IllegalStateException("currentPage�� �� ������ ����(" + totalLastPageNum + ") ���� Ů�ϴ�.");
        }

        // ���� ù��°, ������ ������ ��ȣ ���
        Integer blockLastPageNum = totalLastPageNum;
        Integer blockFirstPageNum = 1;

        // ���� ���� ���, 1������ ��ȯ.
        if(isFixed) {

            Integer mod = totalLastPageNum % pagesPerBlock;
            if(totalLastPageNum - mod >= currentPageNum) {
                blockLastPageNum = (int) (Math.ceil((float)currentPageNum / pagesPerBlock) * pagesPerBlock);
                blockFirstPageNum = blockLastPageNum - (pagesPerBlock - 1);
            } else {
                blockFirstPageNum = (int) (Math.ceil((float)currentPageNum / pagesPerBlock) * pagesPerBlock)
                        - (pagesPerBlock - 1);
            }

            // assert blockLastPageNum % pagesPerBlock == 0;
            // assert (blockFirstPageNum - 1) % pagesPerBlock == 0;
        } else {
            // ����� �Ѱ�� ��� (��: 5->2, 9->4)
            Integer mid = pagesPerBlock / 2;

            // ���� ù��°, ������ ������ ��ȣ ���
            if(currentPageNum <= pagesPerBlock) {
                blockLastPageNum = pagesPerBlock;
            } else if(currentPageNum < totalLastPageNum - mid) {
                blockLastPageNum = currentPageNum + mid;
            }

            blockFirstPageNum = blockLastPageNum - (pagesPerBlock - 1);

            if(totalLastPageNum < pagesPerBlock) {
                blockLastPageNum = totalLastPageNum;
                blockFirstPageNum = 1;
            }
            // assert blockLastPageNum == currentPageNum + mid;
            // assert (blockFirstPageNum - 1) % pagesPerBlock == 0;
        }

        // ������ ��ȣ �Ҵ�
        List<Integer> pageList = new ArrayList<>();
        for(int i = 0, val = blockFirstPageNum; val <= blockLastPageNum; i++, val++) {
            pageList.add(i, val);
        }


        Map<String, Object> result = new HashMap<>();
        result.put("isPrevExist", (int)currentPageNum > (int)pagesPerBlock);
        result.put("isNextExist", blockLastPageNum != 1 ? (int)blockLastPageNum != (int)totalLastPageNum : false);
        result.put("totalLastPageNum", totalLastPageNum);
        result.put("blockLastPageNum", blockLastPageNum);
        result.put("blockFirstPageNum", blockFirstPageNum);
        result.put("currentPageNum", currentPageNum);
        result.put("totalPostCount", totalPostCount);
        result.put("pagesPerBlock", pagesPerBlock);
        result.put("postsPerPage", postsPerPage);
        result.put("pageList", pageList);
        return result;
    }

    public Map<String, Object> getElasticBlock(Integer currentPageNum) {
        return this.getBlock(currentPageNum, false);
    }

    public Map<String, Object> getFixedBlock(Integer currentPageNum) {
        return this.getBlock(currentPageNum, true);
    }


    /*
    public static void main(String[] args) throws Exception {
        final int PAGES_PER_BLOCK = 10;
        final int POST_PER_PAGE = 10;

        // �� �Խñ� ��
        long totalPostCount = 446;

        // �ν��Ͻ� ����
        tablepagination paginator = new tablepagination(PAGES_PER_BLOCK, POST_PER_PAGE, totalPostCount);

        

    }*/
}