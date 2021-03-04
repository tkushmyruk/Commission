<#import "common/common.ftlh" as com>
<@com.main>
    <h2>${facultyName}</h2>
    <div>Add Address Information</div>
    <form method="post">
    <div><label>City : <input type="text" name="city"></label></div>
    <div><label>Street : <input type="text" name="street"></label></div>
    <div><label>Building : <input type="text" name="building"></label></div>
    <div><label>Apartment : <input type="text" name="apartment"></label></div>
    <div><label>PostCode : <input type="text" name="postCode"></label></div>
        <button type="submit">Save</button>
    </form>
</@com.main>