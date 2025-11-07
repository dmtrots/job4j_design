create or replace view vw_items_full_info as
select
    i.id as item_id,
    i.title,
    i.description as item_description,
    i.date_created,
    u.username as author_username,
    u.email as author_email,
    r.name as role_name,
    r.description as role_description,
    c.name as category_name,
    s.name as state_name,
    count(distinct cm.id) as comments_count,
    count(distinct a.id) as attachments_count,
    string_agg(distinct cm.comment, '; ') as all_comments,
    string_agg(distinct a.file_name, '; ') as all_files
from items i
left join users u on u.id = i.user_id
left join roles r on r.id = u.role_id
left join categories c on c.id = i.category_id
left join states s on s.id = i.state_id
left join comments cm on cm.item_id = i.id
left join attachs a on a.item_id = i.id
group by
    i.id, i.title, i.description, i.date_created,
    u.username, u.email, r.name, r.description,
    c.name, s.name;