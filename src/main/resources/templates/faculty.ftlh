<#import "common/common.ftlh" as com>
<@com.main>
    <br/>
    <br/>
    <h2>List of Faculties</h2>
    <form method="get">
        <div><p>Gender</p>
            <p><input name="sorting" type="radio" checked="checked" value="name">By name</p>
            <p><input name="sorting" type="radio" value="places">By free places</p>
        </div>
        <button type="submit">Sort</button>
    </form>
   <div>
       <#list faculties as faculty>
           <div>${faculty.facultyName}</div>
           <div><a href="/faculties/redaction/${faculty.facultyName}">Change faculty</a></div>
           <div>
           <a href="/student-order/${faculty.facultyName}">Choose this faculty</a>
           </div>
           <div>
           <a href="/faculties/information/${faculty.facultyName}">More information about faculty</a>
           </div>
       </#list>
   </div>
    <div>
        <a href="/faculties/redaction">Redact Faculties</a>
    </div>
</@com.main>