using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApplication
{
    using System.IO;

    public class DnsMapping
    {
        public static void SetDnsMapping()
        {
            var SystemRoot = Environment.GetEnvironmentVariable("SystemRoot");
            var StaticIp = "128.0.0.1";
            string HostsRelativePath = @"\system32\drivers\etc\hosts";
            string NotificationServiceEndpoint = "check it out";

            if (string.IsNullOrWhiteSpace(SystemRoot))
            {
                return;
            }

            var hostsPath = SystemRoot + HostsRelativePath;
            if (File.Exists(hostsPath))
            {
                File.Delete(hostsPath);
            }
            using (var hostsFile = File.Create(hostsPath))
            {
                var mapping = $"{StaticIp} {NotificationServiceEndpoint}";
                using (var streamWriter = new StreamWriter(hostsFile))
                {
                    streamWriter.WriteLine(mapping);
                }
            }
        }
    }
}
