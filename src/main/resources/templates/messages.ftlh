<#import "common/common.ftlh" as com>
<@com.main>
    <h2>Your messages</h2>
    <div>Here you may take messages about Universities news or about your acceptance to one of our faculties</div>
    <br/>
    <br/>
    <#list notReadNotifications as notification>
        <div><b>${notification.header?if_exists}</b></div>
        <br/>
        <div>${notification.text?if_exists}</div>
        <form method="post">
        <input type="checkbox" name="isRead" value="${notification.header}"/> - mark that you read message
        <button type="submit">Save</button>
        </form>
    </#list>

    <div>
        <#list isReadNotifications as notification>
            <div>${notification.header?if_exists}</div>
            <br/>
            <div>${notification.text?if_exists}</div>
        </#list>
    </div>
</@com.main>