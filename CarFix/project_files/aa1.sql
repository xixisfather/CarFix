
select * from tm_stock_in si , tm_stockin_detail sid where si.id = sid.stock_id  and sid.partinfo_id =1;
select * from tm_stock_out so , tm_stockout_detail sod where so.id = sod.stockout_id  and sod.partinfo_id =1;
select * from TB_MAINTAIN_PART_CONTENT m where m.part_id = 1;



select si.in_type tp , sum(sid.quantity) in_sum from tm_stock_in si , tm_stockin_detail sid where si.id = sid.stock_id  and sid.partinfo_id =4 group by si.in_type
UNION 
select so.out_type tp , sum(sod.quantity) out_sum  from tm_stock_out so , tm_stockout_detail sod where so.id = sod.stockout_id  and sod.partinfo_id =4 group by so.out_type
UNION
select 20 tp ,sum(m.part_quantity) ma_sum  from TB_MAINTAIN_PART_CONTENT m where m.part_id = 4;
 

select m.stock_out_date , 20 tp ,m.part_quantity , m.price ,part_quantity*m.price , c.customer_Name , null
from TB_MAINTAIN_PART_CONTENT m  ,TB_FIX_ENTRUST fe , tb_customer c
where fe.CUSTOMER_ID = c.id and fe.id = m.entrust_id 
union all
select si.arrive_date,si.in_type tp,sid.quantity,sid.price,sid.quantity*sid.price , c.customer_Name , u.user_real_name	 
 from tm_stock_in si , tm_stockin_detail sid ,tb_customer c ,tm_user u
 where si.id = sid.stock_id and c.id = si.supplier_id and u.id = si.user_id
union all
select so.stock_out_date , so.out_type , sod.quantity ,sod.price ,sod.quantity*sod.price , c.customer_Name , u.user_real_name	 
from tm_stock_out so , tm_stockout_detail sod , tb_customer c , tm_user u
where so.id = sod.stockout_id and c.id = so.customer_bill and  u.id = so.user_id  






select * from TB_MAINTAIN_PART_CONTENT




select m.stock_out_date , 20  ,m.part_quantity , m.price ,part_quantity*m.price , c.customer_Name 
from TB_MAINTAIN_PART_CONTENT m  ,TB_FIX_ENTRUST fe , tb_customer c
where fe.CUSTOMER_ID = c.id and fe.id = m.entrust_id
union all
select si.arrive_date,si.in_type tp,sid.quantity,sid.price,sid.quantity*sid.price , c.customer_Name 
 from tm_stock_in si , tm_stockin_detail sid ,tb_customer c ,tm_user u
 where si.id = sid.stock_id and c.id = si.supplier_id 
union all
select so.stock_out_date , so.out_type , sod.quantity ,sod.price ,sod.quantity*sod.price , c.customer_Name  
from tm_stock_out so , tm_stockout_detail sod , tb_customer c , tm_user u
where so.id = sod.stockout_id and c.id = so.customer_bill 