package com.allen.teachingaid.entity;

import java.util.List;

/**
 * Created by Allen Lin on 2016/02/19.
 */
public class JCourse {

    private Data data;
    /**
     * data : {"Course":[{"id":1,"name":"编译原理","remark":"计算机1班周三班","address":"南校区教学楼E302","create_time":"2/18/2016"},{"id":2,"name":"软件工程","remark":"计算机1班周三班","address":"南校区教学楼E302","create_time":"2/18/2016"},{"id":3,"name":"数据库工程","remark":"计算机1班周三班","address":"南校区教学楼E302","create_time":"2/18/2016"}]}
     * info : success
     * status : 0
     */

    private String info;
    private int status;

    public void setData(Data data) {
        this.data = data;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public String getInfo() {
        return info;
    }

    public int getStatus() {
        return status;
    }

    public static class Data {
        /**
         * id : 1
         * name : 编译原理
         * remark : 计算机1班周三班
         * address : 南校区教学楼E302
         * create_time : 2/18/2016
         */

        private List<Course> course;

        public void setCourse(List<Course> course) {
            this.course = course;
        }

        public List<Course> getCourse() {
            return course;
        }

        public static class Course {
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
}
