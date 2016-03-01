--三本台账编号修改
update ledgerPeopleAspirations set serialnumber=(case when ledgerType=11 then substr(serialnumber,1,6)||'01'||substr(serialnumber,9) 
                                                      when ledgerType=12 then substr(serialnumber,1,6)||'02'||substr(serialnumber,9) 
                                                      when ledgerType=16 then substr(serialnumber,1,6)||'03'||substr(serialnumber,9)  
                                                      when ledgerType=13 then substr(serialnumber,1,6)||'04'||substr(serialnumber,9)  
                                                      when ledgerType=110 then substr(serialnumber,1,6)||'05'||substr(serialnumber,9)  
                                                      when ledgerType=14 then substr(serialnumber,1,6)||'06'||substr(serialnumber,9)  
                                                      when ledgerType=17 then substr(serialnumber,1,6)||'07'||substr(serialnumber,9)  
                                                      when ledgerType=18 then substr(serialnumber,1,6)||'08'||substr(serialnumber,9)  
                                                      when ledgerType=19 then substr(serialnumber,1,6)||'09'||substr(serialnumber,9)   
                                                      when ledgerType=15 then substr(serialnumber,1,6)||'10'||substr(serialnumber,9)   
                                                      when ledgerType=111 then substr(serialnumber,1,6)||'11'||substr(serialnumber,9)
                                                  end );
update ledgerPoorPeople set serialnumber=substr(serialnumber,1,6)||'12'||substr(serialnumber,9);                                                 
update ledgerSteadyWork set serialnumber=substr(serialnumber,1,6)||'13'||substr(serialnumber,9);
-- 相关数据更正
update platformAccountLogs set dealdescription=regexp_replace(dealdescription,'纳入低保','落实项目',1,0) where ledgerType not in(2,3);

commit;