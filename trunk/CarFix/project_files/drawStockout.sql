select so.stock_out_code , so.stock_out_date , sh.house_name , pi.part_code , pi.part_name , u.unit_name ,pi.cost_price , sod.price ,sod.quantity , us.user_real_name , d.dept_name
from tm_stock_out so , tm_stockout_detail sod ,tm_store_house sh , tb_part_info pi , tm_unit u , tm_user us , tm_department d
where so.out_type = 12 and so.is_confirm not in (8000) and
sh.id = pi.store_house_id and pi.id = sod.partinfo_id and u.id = pi.unit_id and us.id = so.draw_people and d.id = us.dept_id



select * from tm_stock_out



select d.dept_name,aa.* from  tm_user us , tm_department d ,

(select so.draw_people , sum(sod.price * sod.quantity) sell_price , sum(pi.cost_price* sod.quantity) cost_price
from tm_stock_out so , tm_stockout_detail sod , tb_part_info pi
where so.out_type = 12 and so.is_confirm not in (8000) and so.id = sod.stockout_id and pi.id = sod.partinfo_id
group by so.draw_people) aa

where d.id = us.dept_id  and aa.draw_people = us.id