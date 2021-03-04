<#import "common/common.ftlh" as com>
<@com.main>
    <h2>Your Account</h2>
    <div>Here you can change infromation in your student orders</div>
    <div>Warining! Please be sure that your information is correct!</div>
    <#list studentOrders as studentOrder>

        <div>${studentOrder.getFaculty().getFacultyName()}</div>
        <div>
        <a href="/student/change-student-order/${studentOrder.getFaculty().getFacultyName()}/personal">Change Personal Info</a>
        </div>
        <div>
        <a href="/student/change-student-order/${studentOrder.getFaculty().getFacultyName()}/address">Change Address Info</a>
        </div>
        <div>
        <a href="/student/change-student-order/${studentOrder.getFaculty().getFacultyName()}/passport">Change Passport Info</a>
        </div>
        <div>
        <a href="/student/change-student-order/${studentOrder.getFaculty().getFacultyName()}/certificate">Change Certificate Info</a>
        </div>
        <br/>
        <br/>
    </#list>
</@com.main>