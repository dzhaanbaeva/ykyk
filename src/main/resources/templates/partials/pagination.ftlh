<div  id="pagination">
    <#if hasPrev>
        <a id="loadPrev" class="btn btn-outline-primary" href="${prevPageLink!''}">&lt;&lt;&lt;Назад</a>
    <#else>
        <span class="btn btn-outline-primary" id="loadPrev">Назад</span>
    </#if>

    <#if hasNext>
        <a id="loadNext" class="btn btn-outline-primary" href="${nextPageLink!''}" data-default-page-size="${defaultPageSize!2}">Вперёд&gt;&gt;&gt;</a>
    <#else>
        <span class="btn btn-outline-primary">Вперёд</span>
    </#if>
    <br><br><br>
</div>