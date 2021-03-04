<#import "common/common.ftlh" as com>
<@com.main>
    <h2>Account page</h2>
    <div>For changing your password click here:</div>
    <a href="/student/change-password">Change Pass</a>
    <#if studentStudentOrders??>
        <div>For redaction your information in student order click here: </div>
        <a href="/student/change-student-order">Redact</a>
    <#else >
        <div> For sending your student order click here:</div>
        <a href="/faculties">Faculties</a>
    </#if>
    <div>Messages : </div>
    <a href="/student/messages">Messages</a>
</@com.main>