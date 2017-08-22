<div id="guestdiv" style="display:none;">
  <form >
    <input type="hidden" name="guestid" id="guestid" value="" />
    Guest Name
    <input type="text" id="guestname" name="guestname" placeholder="Guest name.."><br/>

  Relationship
    <input type="text" id="relationship" name="relationship" placeholder="Relationship.."><br/>
    
   Sit with&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <select multiple id="sit_list" name="sit_list" style="
    padding-left: 2px;
    padding-right: 2px;
    padding-top: 2px;
    padding-bottom: 2px;
   ">
      <option value="australia">Australia</option>
      <option value="canada">Canada</option>
      <option value="usa">USA</option>
    </select><br/>
    
    Not Sit with&nbsp;
   <select multiple="multiple" id="not_sit_list" name="not_sit_list" style="
    padding-left: 2px;
    padding-right: 2px;
    padding-top: 2px;
    padding-bottom: 2px;
    margin-left: 11px;
   ">
      <option value="1">Australia</option>
      <option value="canada">Canada</option>
      <option value="2">USA</option>
    </select><br/>
  
    <input style="margin-top: 30px;width:90px;" type="submit" value="Update">
    <input style=" width:90px;margin-top: 30px;margin-left:90px;" type="submit" value="Delete">
  </form>
  </div>