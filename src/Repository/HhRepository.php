<?php

namespace App\Repository;

use App\Entity\Hh;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Hh|null find($id, $lockMode = null, $lockVersion = null)
 * @method Hh|null findOneBy(array $criteria, array $orderBy = null)
 * @method Hh[]    findAll()
 * @method Hh[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class HhRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Hh::class);
    }

    // /**
    //  * @return Hh[] Returns an array of Hh objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('h')
            ->andWhere('h.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('h.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Hh
    {
        return $this->createQueryBuilder('h')
            ->andWhere('h.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
