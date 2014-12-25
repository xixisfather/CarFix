

select * from tb_part_info;
select * from tm_stockout_detail t where t.stockout_id =2;
select *  from tb_maintain_part_content 


select tpi.part_name , cmt.model_name , tpi.store_Location, tpi.cost_price cp , tpi.store_quantity, aa.sell_quantity , bb.sell_price , aa.cost_price ,  bb.sell_price - aa.cost_price lr 
from tb_part_info tpi , TM_CAR_MODEL_TYPE cmt,
(select cc.partinfo_id ,sum(sell_quantity) sell_quantity ,sum(cost_price) cost_price from 
(select sod.partinfo_id  partinfo_id , sum(sod.quantity) sell_quantity ,sum( p.cost_price * sod.quantity) cost_price
 from tm_stock_out so , tm_stockout_detail sod , tb_part_info p
 where so.id = sod.stockout_id and p.id = sod.partinfo_id 
group by sod.partinfo_id
union all
select m.part_id partinfo_id ,sum(m.part_quantity) sell_quantity , sum( p.cost_price * m.part_quantity) cost_price
 from tb_maintain_part_content m ,  tb_part_info p
where m.part_id = p.id
group by part_id) cc
group by cc.partinfo_id) aa left join


(select dd.partinfo_id ,sum(sell_price) sell_price from
(select sod.partinfo_id partinfo_id ,avg(sod.price) * sum(sod.quantity) sell_price
 from tm_stock_out so , tm_stockout_detail sod , tb_part_info p 
 where so.id = sod.stockout_id and p.id = sod.partinfo_id and sod.is_free = 0
group by sod.partinfo_id
union all
select m.part_id partinfo_id ,avg(m.price) * sum(m.part_quantity) sell_price
 from tb_maintain_part_content m ,  tb_part_info p
where m.part_id = p.id and m.is_free = 0
group by part_id) dd 
group by dd.partinfo_id) bb 
on aa.partinfo_id = bb.partinfo_id
where tpi.id = aa.partinfo_id and cmt.id = tpi.car_model_type_id











