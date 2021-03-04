<#import "common/common.ftlh" as com>
<@com.main>
<h2>${facultyName}</h2>
    <div>Faculty studentPersonalInfos</div>
    <div>
        <#if faculty.commissionIsEnd >
        <a href="/faculties/accepted/${faculty.facultyName}">Winners</a>
        <#else>
        <form method="post">
            <button type="submit">Accept Students</button>
        </form>
        </#if>
    </div>
    <#list students as student>
        <div>${student.getStudentPersonalInfo().getFirstName()} ${student.getStudentPersonalInfo().getLastName()}</div>
        <#list student.getCertificate().disciplines as discipline>
                <label>${discipline.disciplineName} - ${discipline.score}</label>
        </#list>
        <br/>
    </#list>
</@com.main>