using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace Web.Models
{
    public class Product
    {
        [Key]
        public int ProductID { get; set; }
        [Required]
        public string Name { get; set;  }
        [Required]
        public int UnitsInStock { get; set; }
        [Required]
        [Column(TypeName = "money")]
        public decimal Unitprice { get; set; }
        public int CategoryID { get; set; }
    }
}
