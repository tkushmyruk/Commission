<#import "common/common.ftlh" as com>
<@com.main>
    <h2>${facultyName}</h2>
    <#if isSended == "true" >
        <div>You already have sended studentPersonalInfo order</div>
        <#else>
    <form method="post">
        <div><p>Education Form</p>
            <p><input name="educationForm" type="radio" value="DAY">Day</p>
            <p><input name="educationForm" type="radio" value="EVENING">Evening</p>
            <p><input name="educationForm" type="radio" value="REMOVE">Remove</p>
        </div>
    <button type="submit">Start registration studentPersonalInfo order</button>
    </form>
    </#if>
</@com.main>