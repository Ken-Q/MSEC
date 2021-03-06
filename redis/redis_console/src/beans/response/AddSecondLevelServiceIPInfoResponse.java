
/**
 * Tencent is pleased to support the open source community by making MSEC available.
 *
 * Copyright (C) 2016 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the GNU General Public License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. You may 
 * obtain a copy of the License at
 *
 *     https://opensource.org/licenses/GPL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the 
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */


package beans.response;

import beans.dbaccess.ServerInfo;
import msec.org.JsonRPCResponseBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Administrator on 2016/1/27.
 */


public class AddSecondLevelServiceIPInfoResponse extends JsonRPCResponseBase {
    public class IPInfo {
        String ip;
        String status_message;
        int cpu_cores;
        int cpu_mhz;
        int cpu_load;
        int memory_avail;
        String set_id;
        int instance_num;
        ArrayList<ServerInfo> servers;

        public IPInfo() {
            status_message="Initializing.";
        }


        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getStatus_message() {
            return status_message;
        }

        public void setStatus_message(String status_message) {
            this.status_message = status_message;
        }

        public int getCpu_cores() {
            return cpu_cores;
        }

        public void setCpu_cores(int cpu_cores) {
            this.cpu_cores = cpu_cores;
        }

        public int getCpu_mhz() {
            return cpu_mhz;
        }

        public void setCpu_mhz(int cpu_mhz) {
            this.cpu_mhz = cpu_mhz;
        }

        public int getCpu_load() {
            return cpu_load;
        }

        public void setCpu_load(int cpu_load) {
            this.cpu_load = cpu_load;
        }

        public int getMemory_avail() {
            return memory_avail;
        }

        public void setMemory_avail(int memory_avail) {
            this.memory_avail = memory_avail;
        }

        public String getSet_id() {
            return set_id;
        }

        public void setSet_id(String set_id) {
            this.set_id = set_id;
        }

        public ArrayList<ServerInfo> getServers() {
            return servers;
        }

        public void setServers(ArrayList<ServerInfo> servers) {
            this.servers = servers;
        }

        public int getInstance_num() {
            return instance_num;
        }

        public void setInstance_num(int instance_num) {
            this.instance_num = instance_num;
        }
    }

    public static Comparator<IPInfo> compByInstance()
    {
        Comparator<IPInfo> comp = new Comparator<IPInfo>(){
            @Override
            public int compare(IPInfo ip1, IPInfo ip2)
            {
                if(ip1.getInstance_num() < ip2.getInstance_num())
                    return -1;
                else if(ip1.getInstance_num() > ip2.getInstance_num())
                    return 1;
                else
                    return ip1.getIp().compareTo(ip2.getIp());
            }
        };
        return comp;
    }

    ArrayList<IPInfo> added_ips;
    public ArrayList<IPInfo> getAdded_ips() {
        return added_ips;
    }

    public void setAdded_ips(ArrayList<IPInfo> added_ips, int instance_memory) {
        for(IPInfo ip: added_ips) {
            ip.setInstance_num(Math.min(ip.getCpu_cores(), ip.getMemory_avail() / instance_memory));
        }
        Collections.sort(added_ips, compByInstance());
        this.added_ips = added_ips;
    }

}


