Select entrust_id , max(entrust_code) entrust_code , max(fix_date) fix_date, max(mcode) maintain_code , max(scode) stockout_code,max(license_code) license_code,
	max(fix_type) fix_type , max(customer_name) customer_name , max(user_real_name) user_real_name ,
 	      isnull(sum(xj),0) as '总成本',
	      isnull(sum(case when out_type = 20 then xj end),0) as '修理成本',
	      isnull(sum(case when out_type = 11 then xj end),0) as '销售成本',
	      isnull(sum(case when is_free = 1 then xj end),0) as '无',
	      isnull(sum(case when is_free = 2 then xj end),0) as '首保',
	      isnull(sum(case when is_free = 3 then xj end),0) as '索赔',
	      isnull(sum(case when is_free = 4 then xj end),0) as '返工',
	      isnull(sum(case when is_free = 5 then xj end),0) as '服务活动',
	      max(banance_Date) banance_Date,max(balance_Code) balance_Code
from (

select fe.id entrust_id,fe.entrust_code,fe.fix_date,m.maintain_code mcode,null scode,ci.license_code,ft.fix_type , c.customer_name , u.user_real_name , m.is_free ,m.part_quantity * pi.cost_price xj,20 out_type ,bb.banance_Date ,bb.balance_Code
from tb_fix_entrust fe ,tb_maintain_part_content m ,tb_car_info ci,tm_fix_type ft , tb_customer c , tm_user u , tb_part_info pi,tm_car_model_type cmt ,Tb_Business_Balance bb
where fe.id = m.entrust_id and fe.car_info_id = ci.id and fe.fix_type_id = ft.id and fe.customer_id = c.id and fe.user_id = u.id and m.part_id = pi.id and cmt.id = ci.model_type_id and bb.id = m.balance_id
and m.is_confirm = 8002 and m.balance_id != null
union all
select  fe.id entrust_id,fe.entrust_code,fe.fix_date ,null mcode, so.stock_out_code scode, ci.license_code,ft.fix_type , c.customer_name , u.user_real_name , sod.is_free,sod.quantity*pi.cost_price xj,11 out_type ,bb.banance_Date,bb.balance_Code
from tm_stock_out so , tm_stockout_detail sod ,tb_fix_entrust fe,tb_car_info ci,tm_fix_type ft , tb_customer c , tm_user u , tb_part_info pi,tm_car_model_type cmt ,Tb_Business_Balance bb
where so.id = sod.stockout_id and  fe.car_info_id = ci.id and fe.fix_type_id = ft.id and fe.customer_id = c.id and fe.user_id = u.id and sod.partinfo_id = pi.id and cmt.id = ci.model_type_id and bb.id = sod.balance_id
 and so.out_type = 11 and so.trust_bill != '' and so.trust_bill = fe.entrust_code and so.is_confirm = 8002 and sod.balance_id != null 
) aa

group by aa.entrust_id