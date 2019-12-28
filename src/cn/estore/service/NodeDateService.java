package cn.estore.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.estore.dao.NodeDateDao;
import cn.estore.domain.NodeDate;

public class NodeDateService {
    NodeDateDao dao = new NodeDateDao();

    public List<HashMap<String, Object>> getNextName(String code) throws SQLException {
        List<HashMap<String, Object>> name = new ArrayList<>();
        for (int i = 0; i < dao.getNextName(code).size(); i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("code", dao.getNextName(code).get(i).getCode());
            map.put("name", dao.getNextName(code).get(i).getName());
            name.add(map);
        }
        return name;
    }
    //获取指定的NodeDate
    public NodeDate getNodeDate(String name) throws SQLException {
        return dao.getNoeDate(name);
    }

}
