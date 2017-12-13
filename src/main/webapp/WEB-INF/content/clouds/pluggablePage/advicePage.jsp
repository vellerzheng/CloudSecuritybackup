
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      layout:decorate="~{blog_indigo/common/layout}">
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!--<title>-->
    <!-- <th:block th:text="${title} + ' - '+ ${webSiteInfo.title}"></th:block> -->
    <!--</title>-->
    <meta name="keywords" content="">
    <meta name="description" content="">
</head>
<body>
<ul class="post-list" layout:fragment="content">
    <li class="post-list-item fade in" th:each="article:${pageInfo.list}">
        <article th:id="${article.id}" class="article-card article-type-post" itemprop="blogPost">
            <div class="post-meta">
                <time class="post-time" th:title="${commons.parseDateTime(article.publishTime)}"
                      th:datetime="${article.publishTime}" itemprop="datePublished"
                      th:text="${commons.parseDateTime(article.publishTime)}">
                </time>
                <ul class="article-category-list">
                    <li class="article-category-list-item">
                        <a class="article-category-list-link" th:href="@{/category/} + ${article.category.name}"
                           th:text="${article.category.name}">
                        </a>
                    </li>
                </ul>
            </div>
            <h3 class="post-title" itemprop="name">
                <!--<span th:text="${article.type}"></span>-->
                <span th:replace="blog_indigo/common/lib::article_prefix (${article})"></span>
                <a class="post-title-link" th:href="@{'/article/'+ ${article.id}}" th:text="${article.title}"></a>
            </h3>
            <div class="post-content" id="post-content" itemprop="postContent">
                <th:block th:text="${article.summary}"></th:block>
                <a th:href="@{'/article/'+ ${article.id}}" class="post-more waves-effect waves-button">
                    阅读全文…
                </a>
            </div>
            <div class="post-footer">
                <ul class="article-tag-list">
                    <li class="article-tag-list-item" th:each="tag:${article.tags}">
                        <a class="article-tag-list-link waves-effect waves-button"
                           th:href="@{/tag/} + ${tag.name}" th:text="${tag.name}">
                        </a>
                    </li>
                </ul>
            </div>
        </article>
    </li>
</ul>

<script th:inline="javascript">
    function page() {
        var element = $('#page');
        var options = {
            bootstrapMajorVersion: 3, //对应的bootstrap版本
            currentPage: [[${pageInfo.pageNum}]], //当前页数，获取从后台传过来的值
            numberOfPages: [[${pageInfo.pageSize}]], //每页页数
            totalPages: [[${pageInfo.pages}]], //总页数，获取从后台传过来的值
//            shouldShowPage: true,//是否显示该按钮
            pageUrl: function (type, page, current) {
                return [[${url}]] + '/' + page;
            },
            itemTexts: function (type, page, current) {//设置显示的样式，默认是箭头
                switch (type) {
                    case "first":
                        return "首页";
                    case "prev":
                        return "上一页";
                    case "next":
                        return "下一页";
                    case "last":
                        return "末页";
                    case "page":
                        return page;
                }
            },
            tooltipTitles: function (type, page, current) {
                switch (type) {
                    case "first":
                        return "前往第一页";
                    case "prev":
                        return "前往上一页";
                    case "next":
                        return "前往下一页";
                    case "last":
                        return "前往最后一页";
                    case "page":
                        return "前往第" + page + "页";
                }
            },
//            //点击事件
//            onPageClicked: function (event, originalEvent, type, page) {
//                location.href = [[${url}]] + '/' + page;
//            }
        };
        element.bootstrapPaginator(options);
    }

    $(function () {
        page();
    })
</script>
</body>

</html>

