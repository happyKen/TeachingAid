package com.allen.teachingaid.entity;

import java.util.List;

/**
 * Created by Allen Lin on 2016/02/18.
 */
public class Course {
    /**
     * data : {"Course":[{"id":1,"name":"编译原理","remark":"计算机1班周三班","address":"南校区教学楼E302","create_time":"2/18/2016"},{"id":2,"name":"软件工程","remark":"计算机1班周三班","address":"南校区教学楼E302","create_time":"2/18/2016"},{"id":3,"name":"数据库工程","remark":"计算机1班周三班","address":"南校区教学楼E302","create_time":"2/18/2016"}]}
     * info : success
     * status : 0
     */
    private List<aCourse> courseList;
    private String info;
    private int status;

    public void setCourseList(List<aCourse> courseList) {
        this.courseList = courseList;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<aCourse> getCourseList() {
        return courseList;
    }

    public String getInfo() {
        return info;
    }

    public int getStatus() {
        return status;
    }

    public static class aCourse {
        /* id : 1
         * name : 编译原理
         * remark : 计算机1班周三班
         * address : 南校区教学楼E302
         * create_time : 2/18/2016
         */
        private int id;
        private String name;
        private String remark;
        private String address;
        private String create_time;

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getRemark() {
            return remark;
        }

        public String getAddress() {
            return address;
        }

        public String getCreate_time() {
            return create_time;
        }
    }

}
