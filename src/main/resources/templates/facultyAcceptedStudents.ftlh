<#import "common/common.ftlh" as com>
<@com.main>
    <h2>All winners</h2>
    <div>
        <#list studentOrders as studentOrder>
            <div>${studentOrder.getStudentPersonalInfo().getFirstName()} ${studentOrder.getStudentPersonalInfo().getLastName()}</div>
            <br/>
        </#list>
    </div>
</@com.main>